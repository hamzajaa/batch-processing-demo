package com.example.spring.batch.ws;

import lombok.SneakyThrows;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class BatchJobController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    private final String TEMP_STORAGE = "C:\\Users\\jaaha\\OneDrive\\Bureau\\batch-files\\";

    @SneakyThrows
    @PostMapping(value = "/importCustomers", consumes = "multipart/form-data")
    public void importCsvToDbJob(@RequestParam("file") MultipartFile file) {

        // file -> path we don't know
        // copy the file to some storage in your VM : get the file path
        // copy the file to DB : get the file path

        String originalFilename = file.getOriginalFilename();
        File fileToImport = new File(TEMP_STORAGE + Objects.requireNonNull(originalFilename));
        file.transferTo(fileToImport.toPath()); // copy the file to the storage
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("fullPathFileName", fileToImport.toPath().toString())
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);

//        if (execution.getExitStatus().getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
//            // delete the file from the TEMP_STORAGE
//            Files.deleteIfExists(fileToImport.toPath());
//        }

    }


}
