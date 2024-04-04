package com.openclassrooms.Openclassrooms_FS_P13_POC.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
	private String name;
	private String content;
}
