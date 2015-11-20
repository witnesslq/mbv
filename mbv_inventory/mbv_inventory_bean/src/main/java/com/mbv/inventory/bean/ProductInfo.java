package com.mbv.inventory.bean;

import java.io.Serializable;
import java.util.List;

public class ProductInfo implements Serializable{

	private static final long serialVersionUID = 5990917159176173846L;

	private String prodClsNum;                     //6位款码
	private String prodName;                       //商品名称
	private String productUrl;                     //主图路径
	private String srcProdName;                    //源商品名称
	private ProductBrandInfo productBrandInfo;     //商品品牌信息
	private String reckonType;                     //核算类别
	private String reckonTypeCode;
	private String prodType;                       //商品类型
	private String prodTypeCode;
	private String basicUom;                       //基本计量单位
	private String basicUomCode;
	private String gender;                         //性别
	private String genderCode;
	private String prodSeason;                     //商品季节
	private String prodSeasonCode;
	private String prodYear;                       //商品年份
	private String planBatch;                      //计划上市批次
	private String editionBillNum;                 //版单号
	private double onBrandPrc;                     //吊牌价
	private String prodStatus;                     //商品状态
	private String description;                    //商品描述
	private String srcBrand;                       //源品牌名称
	private String purMode;                        //采购模式
	private String series;                         //商品系列
	private String seriesCode;
	private String prodTheme;                      //商品主题
	private String prodThemeCode;
	private String prodGroup;                      //商品组别
	private String prodGroupCode;
	private String prodSubGroup;                   //商品分组别
	private String prodSubGroupCode;
	private String prodPartm;                      //商品部门
	private String prodPartmCode;
	private String prodSorts;                      //商品类别
	private String prodSortsCode;
	private String advantage;                      //卖点
	private String saleDate;                       //首上市日期
	private String specCharValue;                  //规格特征值
	private String specCharDesc;                   //规格特征描述
	private String remark;                         //备注
	private List<ProductSkuInfo> productSkuInfos;  //11位SKU码
	
	public String getReckonTypeCode() {
		return reckonTypeCode;
	}
	public void setReckonTypeCode(String reckonTypeCode) {
		this.reckonTypeCode = reckonTypeCode;
	}
	public String getProdTypeCode() {
		return prodTypeCode;
	}
	public void setProdTypeCode(String prodTypeCode) {
		this.prodTypeCode = prodTypeCode;
	}
	public String getBasicUomCode() {
		return basicUomCode;
	}
	public void setBasicUomCode(String basicUomCode) {
		this.basicUomCode = basicUomCode;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getProdSeasonCode() {
		return prodSeasonCode;
	}
	public void setProdSeasonCode(String prodSeasonCode) {
		this.prodSeasonCode = prodSeasonCode;
	}
	public String getSeriesCode() {
		return seriesCode;
	}
	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}
	public String getProdThemeCode() {
		return prodThemeCode;
	}
	public void setProdThemeCode(String prodThemeCode) {
		this.prodThemeCode = prodThemeCode;
	}
	public String getProdGroupCode() {
		return prodGroupCode;
	}
	public void setProdGroupCode(String prodGroupCode) {
		this.prodGroupCode = prodGroupCode;
	}
	public String getProdSubGroupCode() {
		return prodSubGroupCode;
	}
	public void setProdSubGroupCode(String prodSubGroupCode) {
		this.prodSubGroupCode = prodSubGroupCode;
	}
	public String getProdPartmCode() {
		return prodPartmCode;
	}
	public void setProdPartmCode(String prodPartmCode) {
		this.prodPartmCode = prodPartmCode;
	}
	public String getProdSortsCode() {
		return prodSortsCode;
	}
	public void setProdSortsCode(String prodSortsCode) {
		this.prodSortsCode = prodSortsCode;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSpecCharValue() {
		return specCharValue;
	}
	public void setSpecCharValue(String specCharValue) {
		this.specCharValue = specCharValue;
	}
	public String getSpecCharDesc() {
		return specCharDesc;
	}
	public void setSpecCharDesc(String specCharDesc) {
		this.specCharDesc = specCharDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSrcBrand() {
		return srcBrand;
	}
	public void setSrcBrand(String srcBrand) {
		this.srcBrand = srcBrand;
	}
	public String getPurMode() {
		return purMode;
	}
	public void setPurMode(String purMode) {
		this.purMode = purMode;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getProdTheme() {
		return prodTheme;
	}
	public void setProdTheme(String prodTheme) {
		this.prodTheme = prodTheme;
	}
	public String getProdGroup() {
		return prodGroup;
	}
	public void setProdGroup(String prodGroup) {
		this.prodGroup = prodGroup;
	}
	public String getProdSubGroup() {
		return prodSubGroup;
	}
	public void setProdSubGroup(String prodSubGroup) {
		this.prodSubGroup = prodSubGroup;
	}
	public String getProdPartm() {
		return prodPartm;
	}
	public void setProdPartm(String prodPartm) {
		this.prodPartm = prodPartm;
	}
	public String getProdSorts() {
		return prodSorts;
	}
	public void setProdSorts(String prodSorts) {
		this.prodSorts = prodSorts;
	}
	public String getAdvantage() {
		return advantage;
	}
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public String getProdSeason() {
		return prodSeason;
	}
	public void setProdSeason(String prodSeason) {
		this.prodSeason = prodSeason;
	}
	public String getProdYear() {
		return prodYear;
	}
	public void setProdYear(String prodYear) {
		this.prodYear = prodYear;
	}
	public String getPlanBatch() {
		return planBatch;
	}
	public void setPlanBatch(String planBatch) {
		this.planBatch = planBatch;
	}
	public String getEditionBillNum() {
		return editionBillNum;
	}
	public void setEditionBillNum(String editionBillNum) {
		this.editionBillNum = editionBillNum;
	}
	public double getOnBrandPrc() {
		return onBrandPrc;
	}
	public void setOnBrandPrc(double onBrandPrc) {
		this.onBrandPrc = onBrandPrc;
	}
	public String getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}
	public String getReckonType() {
		return reckonType;
	}
	public void setReckonType(String reckonType) {
		this.reckonType = reckonType;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getBasicUom() {
		return basicUom;
	}
	public void setBasicUom(String basicUom) {
		this.basicUom = basicUom;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSrcProdName() {
		return srcProdName;
	}
	public void setSrcProdName(String srcProdName) {
		this.srcProdName = srcProdName;
	}
	public String getProdClsNum() {
		return prodClsNum;
	}
	public void setProdClsNum(String prodClsNum) {
		this.prodClsNum = prodClsNum;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public ProductBrandInfo getProductBrandInfo() {
		return productBrandInfo;
	}
	public void setProductBrandInfo(ProductBrandInfo productBrandInfo) {
		this.productBrandInfo = productBrandInfo;
	}
	public List<ProductSkuInfo> getProductSkuInfos() {
		return productSkuInfos;
	}
	public void setProductSkuInfos(List<ProductSkuInfo> productSkuInfos) {
		this.productSkuInfos = productSkuInfos;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		ProductBrandInfo pbi = this.productBrandInfo;
		sb.append("ProductInfo:prodClsNum = ").append(this.prodClsNum).append(",prodName = ").append(this.prodName).append(",srcProdName = ")
		  .append(this.srcProdName).append(",brandCode = ").append(pbi.getBrandCode()).append(",brandName = ").append(pbi.getBrandName())
		  .append(",srcBrandCode = ").append(pbi.getSrcBrandCode()).append(",srcBrandName = ").append(pbi.getSrcBrandName());
		if(productSkuInfos != null && productSkuInfos.size() > 0){
			for(int i = 0; i < productSkuInfos.size(); i++){
				ProductSkuInfo psi = productSkuInfos.get(i);
				sb.append(",ProductSkuInfo").append(i).append(":").append(psi.toString());
			}
		}
		return sb.toString();
	}
	
}
