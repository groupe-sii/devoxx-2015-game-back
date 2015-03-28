<#function toCssUrl image>
	<#if image.getClass().getSimpleName() == "UriImage">
		<#return statics["fr.sii.survival.util.ImageUrlUtil"].absoluteUrl(image.uri)>
	<#elseif image.getClass().getSimpleName() == "Base64ServerImage">
		<#return "data:"+image.mimetype+";base64,"+image.content>
	</#if>
</#function>