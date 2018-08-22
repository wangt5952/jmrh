const img2base64=(img)=>{
	const getBase64Image=(img)=>{
     var canvas = document.createElement("canvas");
		 var width = img.width;
			var height = img.height;
		 // if(width > height) {
 			// 	if(width > 100) {
 			// 		height = Math.round(height *= 100 / width);
 			// 		width = 100;
 			// 	}
 			// } else {
 			// 	if(height > 100) {
 			// 		width = Math.round(width *= 100 / height);
 			// 		height = 100;
 			// 	}
 			// }
 			canvas.width = width; /*设置新的图片的宽度*/
 			canvas.height = height; /*设置新的图片的长度*/
 			var ctx = canvas.getContext("2d");
 			ctx.drawImage(img, 0, 0, width, height); /*绘图*/
 			var dataURL = canvas.toDataURL("image/png", 0.8);
 			return dataURL
}
var image = new Image();
image.src = img;
image.setAttribute('crossOrigin', 'anonymous'); //允许图片跨域请求、必须后台也允许

  var base64 = getBase64Image(image);
   return base64

}
export default img2base64;
