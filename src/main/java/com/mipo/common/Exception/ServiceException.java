package com.mipo.common.Exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

   private String code;

   public ServiceException(String code) {
      this.code = code;
   }

}
