	package com.co.orientationVocational.app.services.dto;

public class chasideQuestionDto {
	private String identificacion;
	private int[] testQuestion;
	
	public chasideQuestionDto() {}
	
	public chasideQuestionDto(String identificacion, int[] testQuestion) {
		this.identificacion = identificacion;
		this.testQuestion = testQuestion;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the testQuestion
	 */
	public int[] getTestQuestion() {
		return testQuestion;
	}

	/**
	 * @param testQuestion the testQuestion to set
	 */
	public void setTestQuestion(int[] testQuestion) {
		this.testQuestion = testQuestion;
	}
}
