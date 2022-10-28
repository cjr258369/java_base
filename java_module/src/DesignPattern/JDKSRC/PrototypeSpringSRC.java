package DesignPattern.JDKSRC;

/**
 * @date 2022/10/27
 */
public class PrototypeSpringSRC {
    private Integer id = 10;
    private String nickname = "孙悟空";
    private String skill = "72变";
    
    public PrototypeSpringSRC(){
        System.out.println("PrototypeSpringSRC 创建...");
    }

    public PrototypeSpringSRC(Integer id, String nickname, String skill) {
        this.id = id;
        this.nickname = nickname;
        this.skill = skill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "PrototypeSpringSRC{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
