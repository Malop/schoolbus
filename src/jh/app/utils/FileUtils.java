package jh.app.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.os.Environment;
import android.util.Log;

public class FileUtils {
	private static String SDPATH = Environment.getExternalStorageDirectory()
			+ File.separator;;

	public String getSDPATH() {
		return SDPATH;
	}

	/**
	 * ��SD���ϴ����ļ�
	 * 
	 * @throws IOException
	 */
	public static File createSDFile(String fileName) throws IOException {
		File file = new File(SDPATH + fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * ��SD���ϴ���Ŀ¼
	 * 
	 * @param dirName
	 */
	public static File createSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		dir.mkdirs();
		return dir;
	}

	/**
	 * �ж�SD���ϵ��ļ����Ƿ����
	 */
	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}

	/**
	 * ��һ��InputStream���������д�뵽SD����
	 */
	public static File write2SD(String content, String filename) {
		return write2SD(content, "bus", filename);
	}

	/**
	 * ��һ��InputStream���������д�뵽SD����
	 */
	public static File write2SD(String content, String path, String filename) {
		File file = null;
		BufferedWriter bw = null;

		try {
			// ����ļ��в����ڣ��򴴽��ļ���
			if (!isFileExist(path)) {
				createSDDir(path);
			}
			// ����ļ������ڣ��򴴽��ļ�
			if (!isFileExist(path + File.separator + filename)) {
				file = createSDFile(path + File.separator + filename);
			}
			file = new File(SDPATH + path + File.separator + filename);
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("Error", "д�ļ�����!--->OperateSD.write2SD()");
		}
		return file;
	}

	/**
	 * ��SD���ж�������
	 */
	public static String readSD(String path) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		FileInputStream input = null;
		try {
			File f = new File(SDPATH + path);
			input = new FileInputStream(f);
			buffer = new BufferedReader(new InputStreamReader(input));
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
			buffer.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * ��һ��InputStream���������д�뵽SD����
	 */
	public static File write2SDFromInput(String path, String fileName,
			InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			createSDDir(path);
			file = createSDFile(path + fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			while ((input.read(buffer)) != -1) {
				output.write(buffer);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}