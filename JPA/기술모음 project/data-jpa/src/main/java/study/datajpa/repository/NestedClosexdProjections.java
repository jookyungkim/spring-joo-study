package study.datajpa.repository;

public interface NestedClosexdProjections {

    String getUsername();
    TeamInfo getTeam();

    interface  TeamInfo {
        String getName();
    }

}
