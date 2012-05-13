lexer grammar InternalUmlTransition;
@header {
package org.eclipse.papyrus.transition.editor.xtext.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

T11 : 'Activity' ;
T12 : 'StateMachine' ;
T13 : 'OpaqueBehavior' ;
T14 : ',' ;
T15 : 'after' ;
T16 : 'at' ;
T17 : 'when' ;
T18 : '[' ;
T19 : ']' ;
T20 : '/' ;
T21 : 'all' ;

// $ANTLR src "../org.eclipse.papyrus.transition.editor.xtext.ui/src-gen/org/eclipse/papyrus/transition/editor/xtext/ui/contentassist/antlr/internal/InternalUmlTransition.g" 1238
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.eclipse.papyrus.transition.editor.xtext.ui/src-gen/org/eclipse/papyrus/transition/editor/xtext/ui/contentassist/antlr/internal/InternalUmlTransition.g" 1240
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.eclipse.papyrus.transition.editor.xtext.ui/src-gen/org/eclipse/papyrus/transition/editor/xtext/ui/contentassist/antlr/internal/InternalUmlTransition.g" 1242
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.eclipse.papyrus.transition.editor.xtext.ui/src-gen/org/eclipse/papyrus/transition/editor/xtext/ui/contentassist/antlr/internal/InternalUmlTransition.g" 1244
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.eclipse.papyrus.transition.editor.xtext.ui/src-gen/org/eclipse/papyrus/transition/editor/xtext/ui/contentassist/antlr/internal/InternalUmlTransition.g" 1246
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.eclipse.papyrus.transition.editor.xtext.ui/src-gen/org/eclipse/papyrus/transition/editor/xtext/ui/contentassist/antlr/internal/InternalUmlTransition.g" 1248
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.eclipse.papyrus.transition.editor.xtext.ui/src-gen/org/eclipse/papyrus/transition/editor/xtext/ui/contentassist/antlr/internal/InternalUmlTransition.g" 1250
RULE_ANY_OTHER : .;


