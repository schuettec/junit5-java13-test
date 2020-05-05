package com.github.schuettec.mocktest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

	private static final String FIRST_STRING = "FirstString";
	private static final String SECOND_STRING = "SecondString";

	@Mock
	protected InnerModuleInterface innerMock;

	@Mock
	protected OuterModuleInterface outerMock;

	@Test
	public void shouldMockBothWithoutOpens() {
		Mockito.when(innerMock.echo(Mockito.isA(String.class))).thenAnswer(answerWithArgument(String.class));
		Mockito.when(outerMock.echo(Mockito.isA(String.class))).thenAnswer(answerWithArgument(String.class));

		assertEquals(FIRST_STRING, innerMock.echo(FIRST_STRING));
		assertEquals(SECOND_STRING, outerMock.echo(SECOND_STRING));
	}

	public static <T> Answer<T> answerWithArgument(Class<T> returnType) {
		return new Answer<T>() {
			@Override
			public T answer(InvocationOnMock invocation) {
				return invocation.getArgument(0);
			}
		};
	}
}
