package org.Jschedule.Core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;
import org.quartz.CronExpression;

import junit.framework.TestCase;

public class TestQuartz extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void test_one() throws ParseException{
		/*
		 * 매주 화요일,토요일 13시 30분
		 */
		final String expression = "0 30 13 ? * TUE,SAT *";
        final CronExpression cronExpression = new CronExpression(expression);
 
        System.out.println("current : " + format(new Date()));
        final Date d1 = cronExpression.getNextValidTimeAfter(new Date());
        final Date d2 = cronExpression.getNextValidTimeAfter(d1);
        final Date d3 = cronExpression.getNextValidTimeAfter(d2);
        final Date d4 = cronExpression.getNextValidTimeAfter(d3);
 
        System.out.println(format(d1));
        System.out.println(format(d2));
        System.out.println(format(d3));
        System.out.println(format(d4));
	}

	private String format(Date d) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss, E, W");
		return df.format(d) + "주";
	}
	
	private Date toDate(String format) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).parse(format);
	}
	@Test
	public void test_two() throws ParseException {
		// 4월 12일 부터 : 매일 저녁  8시마다
		final String expression = "0 0 20 */1 * ? *";
		final CronExpression exp = new CronExpression(expression);

		Date current = toDate("2015-04-12 00:00:00");
		System.out.println(" current : " + current);
		
		Date d1 = exp.getNextValidTimeAfter(current);
		Date d2 = exp.getNextValidTimeAfter(d1);
		Date d3 = exp.getNextValidTimeAfter(d2);
		System.out.println("4월 12일 20시 : " + format(d1));
		System.out.println("4월 13일 20시 : " + format(d2));
		System.out.println("4월 14일 20시 : " + format(d3));
	}

}
