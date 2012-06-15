/*****************************************************************************
 * Copyright (c) 2012 ApexSoft
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   ApexSoft - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.diagram.sequence.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.papyrus.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.diagram.sequence.edit.parts.InteractionOperandEditPart;

public class ApexSequenceUtil {

	/**
		 * 주어진 EditPartEntry Set에서 해당 AbstractGraphicalEditPart 보다
		 * y 좌표가 아래에 있는 EditPartList 반환 
		 * 
		 * @param agep   기준이 되는 AbstractGraphicalEditPart
		 * @return aep보다 아래에 위치한 EditPart의 List
		 */
		public static List apexGetBelowEditPartList(AbstractGraphicalEditPart agep) {
			
			RootEditPart rootep = agep.getRoot();
			Set<Entry<Object, EditPart>> wholeEditPartEntries = rootep.getViewer().getEditPartRegistry().entrySet();
					
			List<AbstractGraphicalEditPart> belowEditPartList = new ArrayList<AbstractGraphicalEditPart>();		
			
			int yBottomOfAgep = agep.getFigure().getBounds().getBottom().y;
			
			for (Entry<Object, EditPart> aEPEntry : wholeEditPartEntries ) {
				EditPart editPart = aEPEntry.getValue();
				if ( editPart instanceof AbstractGraphicalEditPart ) {
					AbstractGraphicalEditPart agep1 = (AbstractGraphicalEditPart)editPart;
					IFigure figure = agep1.getFigure();
					int yTopThisEP = figure.getBounds().getTop().y;
	
					if ( yTopThisEP >= yBottomOfAgep) {					
						belowEditPartList.add(agep1);
					}
				}	
			}
	/*8
	System.out.println("+++++ below List Start +++++");
	for ( int i = 0 ; i < belowEditPartList.size() ; i++ ) {
		System.out.println("   ["+i+"] : " + belowEditPartList.get(i));				
	}		
	System.out.println("+++++ below List End+++++");
	*/
	
			return belowEditPartList;
		}

	/**
	 * 주어진 EditPartList를 검색하여
	 * y좌표 기준 주어진 AbstractGraphicalEditPart의 바로 아래에 위치한 AbstractGraphicalEditPart 반환
	 * 
	 * @param agep    기준이 되는 AbstractGraphicalEditPart
	 * @param belowEditPartList    검색할 EditPart의 List
	 * @return    y좌표 기준 agep의 바로 아래에 위치한 AbstractGraphicalEditPart
	 */
	public static AbstractGraphicalEditPart apexGetBeneathEditPart(AbstractGraphicalEditPart agep, List belowEditPartList) {
		int gap = 1000000;
		AbstractGraphicalEditPart beneathEditPart = null;
		int yCF = agep.getFigure().getBounds().getBottom().y;
		Iterator it = belowEditPartList.iterator();
		while( it.hasNext()) {
			AbstractGraphicalEditPart sep = (AbstractGraphicalEditPart)it.next();
			int yEP = sep.getFigure().getBounds().getTop().y;
			int thisGap = yEP - yCF;
			if ( thisGap < gap ) {
				gap = thisGap;
				beneathEditPart = sep;
			}
		}
		return beneathEditPart;
	}

	/**
	 * 주어진 EditPart의 모든 children을 Indent하여 출력하는 Util
	 * 코드 내에서 호출 시 전달 된 depth값을 기준으로 Indent 처리
	 * (depth>=0인 어떤 값도 무방하나 호출 시 depth=0 권장) 
	 * 
	 * @param ep     출력될 children을 가진 EditPart
	 * @param depth  Indent 수준
	 */
	public static void apexShowChildrenEditPart(EditPart ep, int depth) {
		List childrenList = ep.getChildren();
		Iterator it = childrenList.iterator();
		
		while ( it.hasNext() ) {
			StringBuffer sb = new StringBuffer();
			for ( int i = 0 ; i < depth ; i++ ) {
				sb.append(" ");	
			}
			
			EditPart o = (EditPart)it.next();
			
			System.out.println(sb.toString() + o.getClass().getSimpleName());
			
			// children 있으면 depth 증가 후 재귀호출
			if ( o.getChildren().size() > 0 ) {
				apexShowChildrenEditPart(o, depth+2);	
			}
		}	
	}
	
	public static List apexGetParentCombinedFragmentList(CombinedFragmentEditPart cfep) {
		return apexGetParentCombinedFragmentList(cfep, new ArrayList());
	}

	public static List apexGetParentCombinedFragmentList(CombinedFragmentEditPart cfep, List parentCombinedFragmentEditParts) {
				
		EditPart opParent = cfep.getParent();

		if ( opParent instanceof InteractionOperandEditPart ) {
			CombinedFragmentEditPart parentCombinedFragmentEditPart = (CombinedFragmentEditPart)opParent.getParent().getParent(); 
			parentCombinedFragmentEditParts.add(parentCombinedFragmentEditPart);
			apexGetParentCombinedFragmentList(parentCombinedFragmentEditPart, parentCombinedFragmentEditParts);			
		}
		return parentCombinedFragmentEditParts;
	}
}
