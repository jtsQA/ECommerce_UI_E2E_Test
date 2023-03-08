package com.saucedemo.enums;

public enum FileDirectory {
	TEST_DATA_FILE("TestData.xlsx"), CONFIG_FILE("config.properties");

	private String directory;

	private FileDirectory(String directory) {
		this.directory = directory;
	}

	public String getDirectory() {
		String dir = System.getProperty("user.dir") + "/src/test/resources/" + directory;
		return dir.trim();
	}
}
