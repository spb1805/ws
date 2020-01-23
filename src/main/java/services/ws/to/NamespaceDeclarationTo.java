// /////////////////////////////////////////////////////////////////////////////
// @ Copyright Surecomp Israel, 2019.
// This document contains proprietary and confidential information, and shall
// not be reproduced, transferred, or disclosed to others, without the prior
// written consent of Surecomp.

package services.ws.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class NamespaceDeclarationTo {
	private String prefix;
	private String uri;
}
