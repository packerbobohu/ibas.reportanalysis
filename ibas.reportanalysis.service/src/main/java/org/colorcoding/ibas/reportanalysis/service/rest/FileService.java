package org.colorcoding.ibas.reportanalysis.service.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.ICondition;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.data.FileData;
import org.colorcoding.ibas.bobas.i18n.I18N;
import org.colorcoding.ibas.bobas.message.Logger;
import org.colorcoding.ibas.bobas.repository.FileRepositoryReadonly;
import org.colorcoding.ibas.bobas.repository.jersey.FileRepositoryService;
import org.colorcoding.ibas.reportanalysis.MyConfiguration;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("file")
public class FileService extends FileRepositoryService {

	public FileService() {
		String workFolder = MyConfiguration.getDataFolder() + File.separator + "report_files";
		workFolder = MyConfiguration.getConfigValue(MyConfiguration.CONFIG_ITEM_REPORT_FILE_FOLDER, workFolder);
		this.getRepository().setRepositoryFolder(workFolder);
	}

	@POST
	@Path("uploadReport")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public OperationResult<FileData> uploadReport(@FormDataParam("file") InputStream fileStream,
			@FormDataParam("file") FormDataContentDisposition fileDisposition, @QueryParam("token") String token) {
		return super.save(fileStream, fileDisposition, token);
	}

	@GET
	@Path("loadReport")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] loadReport(@QueryParam("report") String report, @QueryParam("token") String token,
			@Context HttpServletResponse response) {
		try {
			// 获取导出的文件
			ICriteria criteria = new Criteria();
			ICondition condition = criteria.getConditions().create();
			condition.setAlias(FileRepositoryReadonly.CRITERIA_CONDITION_ALIAS_FILE_NAME);
			condition.setValue(report);
			FileData fileData = this.fetch(criteria, token).getResultObjects().firstOrDefault();
			if (fileData != null) {
				// 数据存在，尝试转为字节数组
				File file = new File(fileData.getLocation());
				long fileSize = file.length();
				if (fileSize > Integer.MAX_VALUE) {
					throw new Exception(I18N.prop("msg_importexport_invaild_file_data"));
				}
				FileInputStream inputStream = new FileInputStream(file);
				byte[] buffer = new byte[(int) fileSize];
				int offset = 0;
				int numRead = 0;
				while (offset < buffer.length
						&& (numRead = inputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
					offset += numRead;
				}
				inputStream.close();
				response.setHeader("content-disposition",
						String.format("attachment;filename=%s", fileData.getFileName()));// 为文件命名
				// response.addHeader("content-type", "application/xml");
				return buffer;
			} else {
				throw new WebApplicationException(404);
			}
		} catch (Exception e) {
			Logger.log(e);
			throw new WebApplicationException(500);
		}
	}
}
