<#include "image.ftl">
<#list animations as animation>
	<#if animation.getClass().getSimpleName() == "SpriteAnimation">
		<#include "animation/sprite.ftl">
	<#elseif animation.getClass().getSimpleName() == "PropertiesAnimation">
		<#include "animation/properties.ftl">
	</#if>
</#list>