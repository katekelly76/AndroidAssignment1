package animalmanagementsystem.katekelly.com.animalmanagement;

/**
 * Created by katekelly on 08/02/2018.
 */
// https://antonioleiva.com/recyclerview-listener//
public interface ClickInterface {

    public void clickDelete(int id);
    public void clickEdit(int id);

    /**
     * Created by katekelly on 22/02/2018.
     */

    class User {


        private String username;
        private String password;

        public User() {
        }



        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


        public User( String username, String password) {

            this.username = username;
            this.password = password;
        }
    }
}
