package org.saai.reservation.api.core;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonHelper
{
	
	/** The helper. */
	private static GsonHelper helper;
	
	/**
	 * Instantiates a new gson helper.
	 */
	private GsonHelper()
	{
		
	}
	
	/**
	 * From json.
	 *
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            the json
	 * @param type
	 *            the type
	 * @return the t
	 * @throws JsonSyntaxException
	 *             the json syntax exception
	 */
	public static <T> T fromJson(String json, Class<T> type) throws JsonSyntaxException
	{
		try
		{
			return new Gson().fromJson(json, type);
		}
		catch (Exception ex)
		{
			throw new JsonSyntaxException("Received JSON object is invalid: "+json);
		}
	}
	
	/**
	 * To json.
	 * @param <T>
	 *
	 * @param object
	 *            the object
	 * @return the string
	 * @throws JsonSyntaxException
	 *             the json syntax exception
	 */
	public static <T> String toJson(T object) throws JsonSyntaxException
	{
		try
		{
			return new Gson().toJson(object);
		}
		catch (Exception ex)
		{
			throw new JsonSyntaxException("Received JSON object is invalid.");
		}
	}
	
}