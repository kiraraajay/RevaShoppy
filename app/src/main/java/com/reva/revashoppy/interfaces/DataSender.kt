package com.reva.revashoppy.interfaces


interface  DataSender {
    fun amountData(
        currentPosion: Int
        , skuId: String
        , productWight: String
        , unitId: String
        , unitName: String
        , mrp: String
        , selectedPrice: String,
        selectedQty: String,
        formattedTotalAmount: String,
        rowAmount: String
    )

    fun onDataUpdated()
}