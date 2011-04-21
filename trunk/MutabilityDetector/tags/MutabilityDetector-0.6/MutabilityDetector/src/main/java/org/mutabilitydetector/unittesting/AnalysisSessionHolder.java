/*
 * Mutability Detector
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Further licensing information for this project can be found in 
 * 		license/LICENSE.txt
 */

package org.mutabilitydetector.unittesting;

import static org.mutabilitydetector.AnalysisSession.createWithCurrentClassPath;

import org.mutabilitydetector.AnalysisResult;
import org.mutabilitydetector.IAnalysisSession;

public class AnalysisSessionHolder {
	private static final IAnalysisSession assertionAnalysisSession = createWithCurrentClassPath();

	public static AnalysisResult analysisResultFor(Class<?> from) {
		return assertionAnalysisSession.resultFor(from.getName());
	}
}