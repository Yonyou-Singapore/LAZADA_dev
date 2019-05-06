package nc.impl.so.restapi.jsonservice.vo.lazada.util;


import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;

/**
 * @author wyd
 *
 */
@Component
public class DownloadMethod {

	
	
	public String executeOneDownloadTask(Callable<Map<String, Object>> task) {
		
		try {

			Future<Map<String, Object>> taskResult = executeOneTask(task);
			Map<String,Object> retMap = taskResult.get();

			String result = getDownloadResultString(retMap);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}

	public String getDownloadResultString(Map<String, Object> retMap) {
		String result = "";
		Integer totalInsert = 0;
		Integer totalUpdate = 0;
		if(retMap.get("insertCount") != null) {
			totalInsert =totalInsert + (Integer)retMap.get("insertCount");
		}
		if(retMap.get("updateCount") != null) {
			totalUpdate = totalUpdate + (Integer)retMap.get("updateCount");
		}
		result = getDownloadResultMsg(totalInsert, totalUpdate);
		return result;
	}


	/**
	 * @param totalInsert
	 * @param totalUpdate
	 * @return
	 */
	public String getDownloadResultMsg(Integer totalInsert, Integer totalUpdate) {
		String result = ""; 
		
		//if (totalInsert > 0)
			result = "本次成功下载 " + totalInsert + " 条";

		//if (totalUpdate > 0) {
			//if (result.length() > 0)
				result += "，更新 " + totalUpdate + " 条";
			//else
			//	result += "本次成功更新 " + totalUpdate + " 条";
		//}
		return result;
	}
	
	
	
	
	/**
	 * @param taskList
	 * @return result
	 * @author wyd
	 * @param <T>
	 */
	public <T> List<Future<T>> executeTaskList(List<Callable<T>> taskList) throws Exception {
	
        //invokeAll批量运行所有任务, submit提交单个任务
    	ExecutorService exec = Executors.newFixedThreadPool(10);
    	// 结果列表:存放任务完成返回的值
        List<Future<T>> resultList = exec.invokeAll(taskList,20,TimeUnit.MINUTES);
        exec.shutdown();
        return resultList;
	}
	
	public <T> Future<T> executeOneTask(Callable<T> task) throws Exception {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		Future<T> result = exec.submit(task);
		exec.shutdown();
		return result;
	}
	
	
	/**
	 * @param taskList
	 * @return
	 */
	public String executeDownloadTask(List<Callable<Map<String, Object>>> taskList) {
		Integer totalInsert = 0;
		Integer totalUpdate = 0;
		try {
			List<Future<Map<String, Object>>> resultList = executeTaskList(taskList);
			
			JSONArray jsonArray = new JSONArray();
			
			if (resultList != null && resultList.size() > 0) {
				/*从future中输出每个任务的返回值*/
	            for (Future<Map<String, Object>> future : resultList) {
	            	
	            	
	            	if(future == null){
	            		System.out.println("future is null!");
	            		continue;
					}
	            	if(future.isCancelled()){
						System.out.println("future is Cancelled!");
						continue;
					}else if(future.isDone()){
	            		//正常完成
	            		System.out.println(("future is done!"));
					}
	            	Map<String, Object> retMap = future.get();
	            	if(retMap == null) {
	            		continue;
	            	}
	            	
	            	jsonArray = (JSONArray) retMap.get("result");
	            	if(retMap.get("insertCount") != null) {
	            		totalInsert =totalInsert + (Integer)retMap.get("insertCount");
	            	}
	            	if(retMap.get("updateCount") != null) {
	            		totalUpdate = totalUpdate + (Integer)retMap.get("updateCount");
	            	}
	            }
			}

			String result = getDownloadResultMsg(totalInsert, totalUpdate);
			return jsonArray.toJSONString();

		} catch (InterruptedException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
}
