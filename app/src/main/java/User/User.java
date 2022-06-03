package User;
//
//public class User {
//    private final String userName;
//    private final String password;
//    private final String leetcodeId;
//    private final String codeforcesId;
//    private final String codechefId;
//
//    public static class Builder {
//        private final String userName;
//        private final String password;
//        private String leetcodeId;
//        private String codeforcesId;
//        private String codechefId;
//
//        public Builder(String userName, String password) {
//            this.userName = userName;
//            this.password = password;
//        }
//
//        public void leetcode(String leetcodeId) {
//            this.leetcodeId = leetcodeId;
//        }
//
//        public void codeforces(String codeforcesId) {
//            this.codeforcesId = codeforcesId;
//        }
//
//        public void codechef(String codechefId) {
//            this.codechefId = codechefId;
//        }
//
//        public User build() {
//            return new User(this);
//        }
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//
//    public String getUserName() {
//        return userName;
//    }
//
//    private User(Builder builder) {
//        this.userName = builder.userName;
//        this.password = builder.password;
//        this.codechefId = builder.codechefId;
//        this.leetcodeId = builder.leetcodeId;
//        this.codeforcesId = builder.codeforcesId;
//    }
//}


public class User {

    private String cfusername;
    private String leetcodeusername;
    private String codechefusername;

    public User(String cfusername, String leetcodeusername) {
        this.cfusername = cfusername;
        this.leetcodeusername = leetcodeusername;
    }

    public void setCfusername(String username) {
        this.cfusername = username;
    }

    public void setLeetcodeusername(String username) {
        this.leetcodeusername = username;
    }

    public void setCodechefusername(String username) {
        this.codechefusername = username;
    }

    public String getCfusername() {
        return this.cfusername;
    }

    public String getLeetcodeusername() {
        return this.leetcodeusername;
    }

    public String getCodechefusername() {
        return this.codechefusername;
    }
}