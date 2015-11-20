package com.mbv.common.constant;

/**
 * @类描述：账务中心公用常量类
 * @2015年5月29日
 * @version
 */
public class MbvConstant {

    /**
     * 用户登陆信息存入session的名称
     */
    public static final String USERLOGIN_SESSION = "USERLOGIN_SESSION";

    /**
     * 用户当前操作的权限名称
     */
    public static final String PERMISSION_MENUS_REQUEST = "permissionMenu";

    /**
     * 用户登陆页面URL
     */
    public static final String LOGIN_INDEX_URL = "/login/userLogin";
    public static final String ADMIN_LOGIN_INDEX_URL = "/login/adminLogin";

    /**
     * 用户登陆操作的URL
     */
    public static final String LOGINING_URL = "/login/index";

    /**
     * 主框架的url
     */
    public static final String MAIN_URL = "/login/main";
    
    public static final String CAPTCHA_VERIFY_URL = "/captcha/verifyCode";
    
    public static final String CAPTCHA_VALIDATE_URL = "/captcha/validateCode";
    
    /**
     * 用户登出URL
     */
    public static final String LOGIN_OUT_URL = "/login/logout";
    
    public static final String LOGIN_PASSWORD_URL = "/login/findPassword";
    
    public static final String LOGIN_PASSWORD_MODIFY_URL = "/login/passwordModify";
    
    public static final String LOGIN_CAPTCHA_URL = "/login/captcha";

    /**
     * Jqgrid 表格增删改操作传入的对应参数
     */
    public static final String JQGRID_OPER_EDIT = "edit";

    /**
     * Jqgrid 表格增删改操作传入的对应参数
     */
    public static final String JQGRID_OPER_ADD = "add";

    /**
     * Jqgrid 表格增删改操作传入的对应参数
     */
    public static final String JQGRID_OPER_DEL = "del";
    
    public static final String DOC_CODE_INIT = "101";
    
    public static final String VALIDATE_PHONE_CODE = "VALIDATE_PHONE_CODE";  
    public static final String VALIDATE_PHONE = "VALIDATE_PHONE";  
    public static final String SEND_CODE_TIME = "SEND_CODE_TIME"; 
    public static final String USER_CODE = "USER_CODE"; 
    public static final String UNIT_CODE = "UNIT_CODE"; 
    
    /**
     * 供应商名称
     */
    public static final String OWNER_NAME = "OWNER_NAME"; 
    /**
     * 仓库编码
     */
    public static final String WAREH_CODE = "WAREH_CODE";
    /**
     * 仓库名称
     */
    public static final String WAREH_NAME = "WAREH_NAME";
    
    public static final String WN_STATUS_INPUT = "0"; 
    
    /**
     * 工单编号 从00000001 开始
     */
    public static final String WN_DOC_CODE = "00000001";
    
    public static final String CREATE_USER = "vendor";
    public static final String LAST_UPDATE_USER = "vendor";
    public static final String DOC_TYPE_GRN = "1";
    public static final String DOC_TYPE_GDN = "2";
    public static final int QUERY_OFFSET_DEFAULT_VALUE = 0;
    public static final int QUERY_LIMITSET_DEFAULT_VALUE = 1;
    public static final int QTY_COMMITTED_DEFAULT_VALUE = 0;
    public static final int QTY_ON_LOCK_DEFAULT_VALUE = 0;
    public static final String MODEL_ADD = "1";
    public static final String MODEL_UPDATE = "2";
    public static final String DOC_STATE_INPUTING = "1";
    public static final String DOC_STATE_CONFIRM = "3";
    public static final String DOC_STATE_DELETE = "4";
    public static final String DOC_STATE_UNDO = "5";
    //发货出库mode类型
    public static final String DOC_STATE_DEG = "1";
    public static final String SYS_PARAM_LAST_UPDATE_TIME = "INVENTORY_LAST_SYNC_TIME";
    public static final String PRODUCT_SKU_TYPE = "2";
    public static final String MBV_RCPT_REASON = "MBV_RCPT_REASON";
    public static final String MBV_RCPT_MODE = "MBV_RCPT_MODE";
    public static final String INVENTORY_ADD_TYPE_GRN = "1";       //库存页面添加出入库类型  1.入库
    public static final String INVENTORY_ADD_TYPE_GDN = "2";       //库存页面添加出入库类型  2.出库
    public static final String MBV_DISP_REASON = "MBV_DISP_REASON";
    public static final String MBV_DISP_MODE = "MBV_DISP_MODE";
    public static final String MBV_GRN_DOC_STATE = "MBV_GRN_DOC_STATE";
    public static final String MBV_GDN_DOC_STATE = "MBV_GDN_DOC_STATE";
    public static final String MBV_DOC_TYPE = "MBV_DOC_TYPE";
    public static final String INV_RCPT_REASON = "INV_RCPT_REASON";
    public static final String INV_RCPT_MODE = "INV_RCPT_MODE";
    public static final String INV_DISP_REASON = "INV_DISP_REASON";
    public static final String INV_DISP_MODE = "INV_DISP_MODE";
    public static final int Query_MAX_COUNT = 1000;
    public final static int PER_PAGE = 1000;
    public static final int FIRST_PAGE = 1;
    public static final String UNIT_STOCK_SUCCESS_CODE = "100";
    public static final int RESPONSE_SUCCESS = 1;
    public static final String QUERY_PRODUCT_NULL = "查不到该款码对应的商品信息";
    public static final String NUMBER_REX = "^[0-9]+$";
    public static final String DISP_MODE_DEG = "2";
    public static final String SEQ_NAME_VPGRN = "vpGrnSeq";
    public static final String SEQ_NAME_VPGDN = "vpGdnSeq";
    public static final String SRC_DOC_TYPE = "发货单";
    public final static String CACHE_BUSINESS_CODE = "unit_product";
	public final static String CACHE_TYPE = "product_for_unit_stock";
	//为获取商品详细信息的缓存--DIY有效时间
    public final static String CACHE_BUSINESS_CODE_RPT = "mbvendor";
	public final static String CACHE_TYPE_RPT = "report_for_prod_dtl";
    
    /**
     * 工单模块
     */
    public static final String MBV_WN_DOC_TYPE = "0"; //缺货
    public static final String MBV_WN_DOC_STATE = "1"; //1提交状态
    public static final String MBV_WN_MQ_TYPE = "887";
    public static final String MBV_WN_MQ_LOG_TYPE = "0";
    public static final String MBV_WN_SEQ_NAME = "vpWnSeq";
    public static final String MBV_WN_SRC_DOC_TYPE = "0"; //缺货
    
    /**
     * 登陆
     */
    public static final String MBV_SYS_ERROR_TIP = "系统忙，请联系管理员！";
    public static final String MBV_SYS_USER_TYPE_VENDOR_ADMIN = "1";
    public static final String MBV_SYS_USER_TYPE_VENDOR_USER = "2";
    public static final String MBV_SYS_USER_TYPE_ADMIN_USER = "3";
    public static final String MBV_SYS_APP_CODE_MBV_WEB = "1";
    public static final String MBV_SYS_APP_CODE_MBV_USER = "2";
    
    /**
     * 订单列表模块
     */
    public static final String MBV_ORDER_DEG_ID = "MBV_ORDER_DEG_ID";
    
    /**
     * 统一商品
     */
	public final static String PRODUCT_TYPE_PROD_CLS = "1";
	public final static String PRODUCT_TYPE_PROD_SKU = "2";
    
}
