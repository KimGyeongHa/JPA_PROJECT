package jpaShop.shop.job.validator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.thymeleaf.util.StringUtils;

public class Validating implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        if(!StringUtils.endsWith(parameters,"csv")){
            throw new JobParametersInvalidException("this is not csv file");
        }
    }
}
