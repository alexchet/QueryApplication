package com.alexchetcuti.azure.coursework;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;
import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.core.*;
import com.microsoft.windowsazure.exception.ServiceException;

public class Common {

	public Common() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String SSCStorageConnectionString = 
			"DefaultEndpointsProtocol=http;" +
			"AccountName=sscdatastorage;" +
			"AccountKey=9OcI4KkZf6FyD/CSOLBQIzbjxiSIcjKVy0QtO6U1z0Ydb/juV4k49MTLWoRMWl124/GyfOwFMSDGN3htKTnq3Q==";

	public static CloudStorageAccount storageConnect()
	{
	    try {
		    // Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount =
			    CloudStorageAccount.parse(SSCStorageConnectionString);
			
			return storageAccount;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return null;
	}
	
	public static void retreiveCameras()
	{
		try
		{
		    // Define constants for filters.
		    final String PARTITION_KEY = "PartitionKey";
		    
		    CloudStorageAccount storageAccount = storageConnect();
		    
		    // Create the table client.
		    CloudTableClient tableClient = storageAccount.createCloudTableClient();
	
		    // Create a cloud table object for the table.
		    CloudTable cloudTable = tableClient.getTableReference("SpeedCameras");
	
		    // Create a filter condition where the partition key is "Smith".
		    String partitionFilter = TableQuery.generateFilterCondition(
		        PARTITION_KEY,
		        QueryComparisons.EQUAL,
		        "43");
	
		    System.out.println(partitionFilter);
		    // Specify a partition query, using "Smith" as the partition key filter.
		    TableQuery<CameraEntity> partitionQuery =
		        TableQuery.from(CameraEntity.class)
		        .where(partitionFilter);
	
		    // Loop through the results, displaying information about the entity.
		    for (CameraEntity entity : cloudTable.execute(partitionQuery)) {
		        System.out.println(entity.getPartitionKey() +
		            " " + entity.getRowKey() +
		            "\t" + entity.toString());
		    }
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
	}
}
