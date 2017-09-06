package com.exam.admin.rongyundemo.service.response;

/**
 * ========================
 * Name: LoginResponse
 * Des:
 * User: 吴飞
 * Date: 2017/8/4 16:08
 * =========================
 */

public class LoginResponse {
    /**
     * code : 200
     * result : {"id":"t1hWCOGvX","token":"B0DA/kKanJviD5xxUzhwsEFIJad0/86YwGxBwz1417WFQi/Vr2OJay26s5IFDffGZaUYRMAkvN0ikvOcTl7RN9JilKZlosfQ"}
     */

    private int code;
    /**
     * id : t1hWCOGvX
     * token : B0DA/kKanJviD5xxUzhwsEFIJad0/86YwGxBwz1417WFQi/Vr2OJay26s5IFDffGZaUYRMAkvN0ikvOcTl7RN9JilKZlosfQ
     */

    private ResultEntity result;

    public void setCode(int code) {
        this.code = code;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {
        private String id;
        private String token;

        public void setId(String id) {
            this.id = id;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getId() {
            return id;
        }

        public String getToken() {
            return token;
        }
    }
}
