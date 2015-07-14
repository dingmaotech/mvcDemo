package com.dingmao.platform.vo.datatables.wrapper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface  AoColumn  {

	public  int[] aDataSort() default { 0 };

	public  String[] asSorting() default "";

	public  boolean bSearchable() default true;

	public  boolean bSortable() default true;

	public  boolean bVisible() default true;

	public  boolean bUseRendered() default false;

	public  String fnCreatedCell() default "";

	public  String fnRender() default "";

	public  int iDataSort() default 0;

	public  String mData() default "";

	public  String mRender() default "";

	public  String sCellType() default "";

	public  String sClass() default "";

	public  String sContentPadding() default "";

	public  String sDefaultContent() default "";

	public  String sName() default "";

	public  String sSortDataType() default "desc";

	public  String sTitle() default "";

	public  String sType() default "";

	public  String sWidth() default "";
}
