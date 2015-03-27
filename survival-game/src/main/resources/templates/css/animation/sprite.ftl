.${animation.name} {
	${vendorPrefix}animation-fill-mode: none;
	${vendorPrefix}animation-direction: normal;
	${vendorPrefix}animation-iteration-count: ${animation.options.count};
	${vendorPrefix}animation-name: ${animation.name};
	${vendorPrefix}animation-duration: ${animation.duration}ms;
	${vendorPrefix}animation-delay: ${animation.options.delay}ms;
	/*${vendorPrefix}animation-timing-function: steps(${animation.frames?size});*/
	background: transparent url("${animation.sprite.image.uri}") no-repeat left center;
}

@${vendorPrefix}keyframes ${animation.name} {
	<#list animation.frames as frame>
		${frame.percentage}% {background-position: -${frame.properties.x}px;} 
	</#list>
} 
