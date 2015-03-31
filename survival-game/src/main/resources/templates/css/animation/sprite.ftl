.${animation.name?lower_case} {
	display: block;
	width: ${animation.sprite.frameWidth}px;
	height: ${animation.sprite.frameHeight}px;
	${vendorPrefix}animation-fill-mode: none;
	${vendorPrefix}animation-direction: normal;
	${vendorPrefix}animation-iteration-count: ${animation.options.count?c};
	${vendorPrefix}animation-name: ${animation.name?lower_case};
	${vendorPrefix}animation-duration: ${(animation.duration / 1000)?c}s;
	${vendorPrefix}animation-delay: ${(animation.options.delay / 1000)?c}s;
	${vendorPrefix}animation-timing-function: steps(${animation.frames?size});
	background: transparent url("${toCssUrl(animation.sprite.image)}") no-repeat left center;
}

@${vendorPrefix}keyframes ${animation.name?lower_case} {
<#--
	CSS animations doesn't support manual steps
	<#list animation.frames as frame>
		${frame.percentage}% {background-position-x: -${(frame.properties.x + animation.sprite.frameWidth)?c}px;} 
	</#list>
-->
	100% {background-position-x: -${animation.sprite.width?c}px;} 
} 
