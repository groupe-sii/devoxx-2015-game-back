<#list animations as animation>
	<#if "SpriteAnimation" == animation.getClass().getSimpleName()>
		<#include "animation/sprite.ftl">
	</#if>
</#list>