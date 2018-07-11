package org.techtown.blogcustomlistview;


/**
 * 리스트 아이템이 갖고 있는 정보, 관리할 데이터
 */
public class ListItem {

    // 이름과 전화번호 정보
    String name;
    String mobile;


    // 생성자
    public ListItem(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    // 데이터에 직접 접근하지 말고, Getter and Setter 설정
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    // 객체의 정보를 toString()으로 출력
    @Override
    public String toString() {
        return "ListItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }


}
