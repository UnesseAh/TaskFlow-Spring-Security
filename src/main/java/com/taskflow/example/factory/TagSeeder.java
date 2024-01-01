package com.taskflow.example.factory;

import com.taskflow.example.model.Tag;
import com.taskflow.example.repository.TagRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TagSeeder {
    private final TagRepository tagRepository;

    public TagSeeder(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    private final Tag[] tags = {
        Tag.builder().name("tag1").build(),
        Tag.builder().name("tag2").build(),
        Tag.builder().name("tag3").build(),
        Tag.builder().name("tag4").build(),
        Tag.builder().name("tag5").build(),
        Tag.builder().name("tag6").build(),
    };

    public void seed(){
        if(tagRepository.findAll().isEmpty()){
            Arrays.stream(tags).forEach(tag -> {
                tagRepository.save(tag);
            });
        }
    }



}
