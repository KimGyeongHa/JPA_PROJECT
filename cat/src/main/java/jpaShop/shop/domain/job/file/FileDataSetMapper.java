package jpaShop.shop.domain.job.file;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class FileDataSetMapper implements FieldSetMapper<FileData> {

    public FileData mapFieldSet(FieldSet fieldSet) {

        return FileData.builder()
                .id(fieldSet.readString(0))
                .lastName(fieldSet.readString(1))
                .firstName(fieldSet.readString(2))
                .position(fieldSet.readString(3))
                .birthYear(fieldSet.readInt(4))
                .debutYear(fieldSet.readInt(5))
                .build();
    }
}
