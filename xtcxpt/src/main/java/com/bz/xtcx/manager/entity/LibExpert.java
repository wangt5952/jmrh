package com.bz.xtcx.manager.entity;

/**
 * 专家库
 * @author zhangkj
 *
 */
public class LibExpert extends BaseLib{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1193852615185056708L;
	
	private String research_field;//研究领域
	private String research_area;//研究方向
	private String work_unit;//工作单位
	private String success_record;//主要学术成就/研究成果/管理成就
	private String project_desc;//主要产学研合作项目情况（国防军工类项目）
	
	public String getResearch_field() {
		return research_field;
	}
	public String getResearch_area() {
		return research_area;
	}
	public String getWork_unit() {
		return work_unit;
	}
	public String getSuccess_record() {
		return success_record;
	}
	public String getProject_desc() {
		return project_desc;
	}
	public void setResearch_field(String research_field) {
		this.research_field = research_field;
	}
	public void setResearch_area(String research_area) {
		this.research_area = research_area;
	}
	public void setWork_unit(String work_unit) {
		this.work_unit = work_unit;
	}
	public void setSuccess_record(String success_record) {
		this.success_record = success_record;
	}
	public void setProject_desc(String project_desc) {
		this.project_desc = project_desc;
	}
}
