class Tools {
    constructor(){
        this.urlParrent = "http://localhost:9008";
    }
    get(url,data,async) {
        let param = '';
        Object.keys(data).forEach((item,index) => {
            if (Object.keys(data).length - index === 1) {
                param += item + "=" + data[item];
            } else {
                param += item + "=" + data[item] + "&";
            }
        });
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();
            xhr.open('GET', this.urlParrent + url + "?" + param, async);
            xhr.onreadystatechange = function () {
                if (this.readyState === 4) {
                    if(this.status === 200){
                        resolve(JSON.parse(this.responseText), this);
                    } else {
                        let result = {code: this.status, reponse: this.responseText};
                        reject(result, this);
                    }
                }
            };
            xhr.send();
        })
    }

    post(url,data,async) {
        let formData = new FormData();
        for (let key in data) {
            formData.append(key,data[key]);
        }
        return new Promise((resolve, reject) => {
            let xhr = new XMLHttpRequest();
            xhr.open('POST', this.urlParrent + url, async);
            xhr.onreadystatechange = function () {
                if (this.readyState === 4) {
                    if(this.status === 200){
                        resolve(JSON.parse(this.responseText), this);
                    } else {
                        let result = {code: this.status, reponse: this.responseText};
                        reject(result, this);
                    }
                }
            };
            xhr.send(formData);
        })
    }

    static getUrlParam(name){
        let url = document.location.toString();
        let arrObj = url.split("?");

        if (arrObj.length > 1) {
            let arrPara = arrObj[1].split("&");
            let arr;

            for (let i = 0; i < arrPara.length; i++) {
                arr = arrPara[i].split("=");

                if (arr != null && arr[0] === name) {
                    return arr[1];
                }
            }
            return "";
        }
        else {
            return "";
        }
    }
}

export {Tools}