package trainingpart5;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EToEPojo {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @JsonProperty("name")
    public String name;
    @JsonProperty("job")
    public String job;
}
