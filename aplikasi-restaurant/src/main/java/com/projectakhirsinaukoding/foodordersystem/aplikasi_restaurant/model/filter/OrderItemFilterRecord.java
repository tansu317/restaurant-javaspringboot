package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter;

public record OrderItemFilterRecord(String orderId,     // filter berdasarkan Order tertentu
                                    String menuId,      // filter berdasarkan Menu tertentu
                                    Integer minQuantity,// filter jumlah minimal
                                    Integer maxQuantity,// filter jumlah maksimal
                                    Double minPrice,    // filter harga minimal
                                    Double maxPrice   ) {
}
