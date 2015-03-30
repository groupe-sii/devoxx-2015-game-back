.${animation.name} {
	${vendorPrefix}animation-fill-mode: none;
	${vendorPrefix}animation-direction: normal;
	${vendorPrefix}animation-iteration-count: ${animation.options.count?c};
	${vendorPrefix}animation-name: ${animation.name};
	${vendorPrefix}animation-duration: ${animation.duration?c}ms;
	${vendorPrefix}animation-delay: ${animation.options.delay?c}ms;
	${vendorPrefix}animation-timing-function: steps(${animation.frames?size});
	background: transparent url("${toCssUrl(animation.sprite.image)}") no-repeat left center;
}

@${vendorPrefix}keyframes ${animation.name} {
	<#list animation.frames as frame>
		${frame.percentage}% {background-position: -${frame.properties.x}px;} 
	</#list>
} 
