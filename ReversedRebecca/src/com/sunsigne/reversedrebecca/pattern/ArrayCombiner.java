package com.sunsigne.reversedrebecca.pattern;

import java.lang.reflect.Array;

public class ArrayCombiner<T> {

	@SuppressWarnings("unchecked")
	public T[] combine(Class<T> clazz, T[] array, T... elements) {

		T[] combinedArray = (T[]) Array.newInstance(clazz, array.length + elements.length);
		int index = 0;

		if (array.length > 0)
			for (T tempElement : array) {
				combinedArray[index] = tempElement;
				index++;
			}

		if (elements.length > 0)
			for (T tempElement : elements) {
				combinedArray[index] = tempElement;
				index++;
			}

		return combinedArray;
	}

}
