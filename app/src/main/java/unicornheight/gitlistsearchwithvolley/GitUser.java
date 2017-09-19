package unicornheight.gitlistsearchwithvolley;

/**
 * Created by deboajagbe on 9/19/17.
 */

public class GitUser {

    String name;
    String gitUrl;
    String avatar;

    public GitUser(String name, String avatar, String gitUrl) {
        this.name = name;
        this.avatar = avatar;
        this.gitUrl = gitUrl;
    }

    public String getName() {
        return name;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getAvatar(){
        return avatar;
    }
}
