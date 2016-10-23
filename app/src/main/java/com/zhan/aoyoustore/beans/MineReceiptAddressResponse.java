package com.zhan.aoyoustore.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${keke} on 16/10/22.
 */
public class MineReceiptAddressResponse {
    private boolean success;
    private int status;
    private List<AddressInfo> msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AddressInfo> getMsg() {
        return msg;
    }

    public void setMsg(List<AddressInfo> msg) {
        this.msg = msg;
    }

    public static class AddressInfo implements Serializable {
        private int AddressId;
        private String ReceiverPerson;
        private String ReceiverPhone;
        private String Address;
        private boolean IsDefault;
        private boolean isSelected=false;

        @Override
        public String toString() {
            return "AddressInfo{" +
                    "AddressId=" + AddressId +
                    ", ReceiverPerson='" + ReceiverPerson + '\'' +
                    ", ReceiverPhone='" + ReceiverPhone + '\'' +
                    ", Address='" + Address + '\'' +
                    ", IsDefault=" + IsDefault +
                    ", isSelected=" + isSelected +
                    '}';
        }

        public int getAddressId() {
            return AddressId;
        }

        public void setAddressId(int AddressId) {
            this.AddressId = AddressId;
        }

        public String getReceiverPerson() {
            return ReceiverPerson;
        }

        public void setReceiverPerson(String ReceiverPerson) {
            this.ReceiverPerson = ReceiverPerson;
        }

        public String getReceiverPhone() {
            return ReceiverPhone;
        }

        public void setReceiverPhone(String ReceiverPhone) {
            this.ReceiverPhone = ReceiverPhone;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public boolean isIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(boolean IsDefault) {
            this.IsDefault = IsDefault;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }
    }

    @Override
    public String toString() {
        return "CartOrderAddressResponse{" +
                "success=" + success +
                ", status=" + status +
                ", msg=" + msg +
                '}';
    }

}
