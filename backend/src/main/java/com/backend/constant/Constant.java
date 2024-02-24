package com.backend.constant;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    //    public static final String gatherStock = "tồn kho tập kết";
//    public static final String transactionStock = "tồn kho giao dịch";
//    public static final String customerToTransaction = "từ khách hàng đến điểm giao dịch";
//    public static final String transactionToCustomer = "từ điểm giao dịch đến khách hàng";
//    public static final String transactionToGather = "từ điểm giao dịch đến điểm tập kết";
//    public static final String gatherToTransaction = "từ điểm tập kết đến điểm giao dịch";
//    public static final String toOtherGather = "đến điểm tập kết khác";
    public static final String DOCUMENT = "tài liệu";
    public static final String GOOD = "hàng hóa";
    public static final String ON_DELIVERING = "đang trên đường";
    public static final String FAILED = "thất bại";
    public static final String SUCCESSFUL = "thành công";

    public static final String onDeliverCustomerToTransaction = ON_DELIVERING + " từ khách hàng đến điểm giao dịch";
    public static final String failedCustomerToTransaction = "vận chuyển từ khách hàng đến điểm giao dịch " + FAILED;
    public static final String successfulTransactionStock = "vận chuyển đến kho giao dịch " + SUCCESSFUL;
    public static final String onDeliverTransactionToGather = ON_DELIVERING + " từ điểm giao dịch đến điểm tập kết";
    public static final String failedTransactionToGather = "vận chuyển từ điểm giao dịch đến điểm tập kết " + FAILED;
    public static final String successfulGatherStock = "vận chuyển đến kho tập kết " + SUCCESSFUL;
    public static final String onDeliverToOtherGather = ON_DELIVERING + " đến điểm tập kết thứ hai";
    public static final String failedToOtherGather = "vận chuyển đến điểm tập kết thứ hai " + FAILED;
    public static final String onDeliverGatherToTransaction = ON_DELIVERING + " từ điểm tập kết đến điểm giao dịch";
    public static final String failedGatherToTransaction = "vận chuyển từ điểm tập kết đến điểm giao dịch " + FAILED;
    public static final String onDeliverTransactionToCustomer = ON_DELIVERING + " từ điểm giao dịch đến khách hàng";
    public static final String failedTransactionToCustomer = "vận chuyển từ điểm giao dịch đến khách hàng " + FAILED;
    public static final String successfulTransactionToCustomer = "vận chuyển từ điểm giao dịch đến khách hàng " + SUCCESSFUL;

    public static List<String> stateList() {
        List<String> res = new ArrayList<>();
        res.add(onDeliverCustomerToTransaction);
        res.add(failedCustomerToTransaction);
        res.add(successfulTransactionStock);
        res.add(onDeliverTransactionToGather);
        res.add(failedTransactionToGather);
        res.add(successfulGatherStock);
        res.add(onDeliverToOtherGather);
        res.add(failedToOtherGather);
        res.add(onDeliverGatherToTransaction);
        res.add(failedGatherToTransaction);
        res.add(onDeliverTransactionToCustomer);
        res.add(failedTransactionToCustomer);
        res.add(successfulTransactionToCustomer);

        return res;
    }
}
