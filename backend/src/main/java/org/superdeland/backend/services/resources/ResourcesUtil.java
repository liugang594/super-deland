package org.superdeland.backend.services.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.bind.JAXBException;

import org.superdeland.backend.services.index.IndexItem;
import org.superdeland.backend.services.index.IndexType;
import org.superdeland.backend.services.index.IndexUtil;

public class ResourcesUtil {

	private static ReadWriteLock lock = new ReentrantReadWriteLock();

	public static IndexItem createResource(String name, String parentId,
			boolean isDirectory) {
		lock.writeLock().lock();
		try {
			IndexItem indexById = IndexUtil.getIndexById(parentId);
			File file = null;
			if (indexById != null) {
				String path = indexById.getPath();
				file = new File(path + "/" + name);
			} else {
				file = new File("workspace/" + name);
			}
			if (isDirectory) {
				file.mkdir();
			} else {
				file.createNewFile();
			}
			String uuid = UUID.randomUUID().toString();
			return IndexUtil.addIndex(uuid, name,
					isDirectory ? IndexType.FOLDER : IndexType.FILE,
					(indexById == null ? "workspace" : indexById.getPath())
							+ "/" + name);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		return null;
	}

	public static boolean deleteResource(String id) {
		lock.writeLock().lock();
		try {
			IndexItem indexById = IndexUtil.getIndexById(id);
			if (indexById != null) {
				String path = indexById.getPath();
				if (new File(path).delete()) {
					IndexUtil.removeIndex(indexById.getId());
				}
			}
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		return false;
	}

	public static boolean updateContent(String id, String content) {
		lock.writeLock().lock();
		try {
			IndexItem resource = getResource(id);
			String path = resource.getPath();
			File file = new File(path);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file)));
			bw.write(content);
			bw.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		return false;
	}

	public static IndexItem[] getResources(String id) {
		lock.readLock().lock();
		try {
			File parentFile = null;
			if (id == null || "".equals(id)) {
				parentFile = new File("workspace");
			} else {
				parentFile = new File(IndexUtil.getIndexById(id).getPath());
			}
			if (parentFile == null || parentFile.isFile()) {
				return new IndexItem[0];
			}
			IndexItem[] items = new IndexItem[parentFile.list().length];
			IndexItem[] indexes = IndexUtil.getIndexes();
			int i = 0;
			for (IndexItem ii : indexes) {
				if (new File(ii.getPath()).getParentFile().equals(parentFile)) {
					items[i++] = ii;
				}
			}
			return items;
		} finally {
			lock.readLock().unlock();
		}
	}

	public IndexItem getIndexItemByPath(String path) {
		lock.readLock().lock();
		try {
			if (path == null || path.equals("")) {
				return null;
			} else {
				IndexItem[] indexes = IndexUtil.getIndexes();
				for (IndexItem ii : indexes) {
					if (path.equals(ii.getPath())) {
						return ii;
					}
				}
			}
			return null;
		} finally {
			lock.readLock().unlock();
		}
	}

	public static IndexItem getResource(String id) {
		lock.readLock().lock();
		try {
			return IndexUtil.getIndexById(id);
		} finally {
			lock.readLock().unlock();
		}
	}

}
