package User;

public class User {
    private final String userName;
    private final String password;
    private final String leetcodeId;
    private final String codeforcesId;
    private final String codechefId;

    public static class Builder {
        private final String userName;
        private final String password;
        private String leetcodeId;
        private String codeforcesId;
        private String codechefId;

        public Builder(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public void leetcode(String leetcodeId) {
            this.leetcodeId = leetcodeId;
        }

        public void codeforces(String codeforcesId) {
            this.codeforcesId = codeforcesId;
        }

        public void codechef(String codechefId) {
            this.codechefId = codechefId;
        }

        public User build() {
            return new User(this);
        }
    }

    public String getPassword() {
        return password;
    }


    public String getUserName() {
        return userName;
    }

    private User(Builder builder) {
        this.userName = builder.userName;
        this.password = builder.password;
        this.codechefId = builder.codechefId;
        this.leetcodeId = builder.leetcodeId;
        this.codeforcesId = builder.codeforcesId;
    }
}
