package findAnyCat.cat.job.file;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileData {
    private String ID;
    private String lastName;
    private String firstName;
    private String position;
    private int birthYear;
    private int debutYear;
    private int yearsExperience;

    @Builder
    public FileData(String id,String lastName, String firstName, String position, int birthYear, int debutYear, int yearsExperience) {
        this.ID = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.birthYear = birthYear;
        this.debutYear = debutYear;
        this.yearsExperience = yearsExperience;
    }
}
