package com.trilogyed.product.viewmodel;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductViewModel {
    private int productId;
    @NotEmpty(message = "Please supply an productName")
    private String productName;
    @NotEmpty(message = "Please supply an productDescription")
    private String productDescription;
    @NotNull(message = "Please supply a listPrice")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "999999.99", inclusive = true)
    private BigDecimal listPrice;
    @NotNull(message = "Please supply a unitCost")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "999999.99", inclusive = true)
    private BigDecimal unitCost;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductViewModel that = (ProductViewModel) o;
        return productId == that.productId &&
                productName.equals(that.productName) &&
                productDescription.equals(that.productDescription) &&
                listPrice.equals(that.listPrice) &&
                unitCost.equals(that.unitCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productDescription, listPrice, unitCost);
    }
}
