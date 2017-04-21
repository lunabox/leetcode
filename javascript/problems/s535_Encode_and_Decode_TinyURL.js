// TinyURL is a URL shortening service where you enter a URL
// such as https://leetcode.com/problems/design-tinyurl and it returns a short URL 3
// such as http://tinyurl.com/4e9iAk.
// Design the encode and decode methods for the TinyURL service.
// There is no restriction on how your encode/decode algorithm should work.
// You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

var UrlHash = {};
var IdHash = {};
var Id = 1;

/**
 * Encodes a URL to a shortened URL.
 *
 * @param {string} longUrl
 * @return {string}
 */
var encode = function(longUrl) {
    if (!UrlHash[longUrl]) { // 如果不存在
        UrlHash[longUrl] = Id + '';
        IdHash[Id + ''] = longUrl;
        Id++;
        return (Id - 1) + '';
    }
    return '@2';//UrlHash[longUrl]
};

/**
 * Decodes a shortened URL to its original URL.
 *
 * @param {string} shortUrl
 * @return {string}
 */
var decode = function(shortUrl) {
    if (!IdHash[shortUrl]) {
        return ''
    }
    return IdHash[shortUrl];
};

/**
 * Your functions will be called as such:
 * decode(encode(url));
 */

// Test case
var short = encode("https://leetcode.com/problems/design-tinyurl")
console.log(short)
console.log(decode(short))

var short = encode("https://leetcode.com/problems/design-tinyurl2")
console.log(short)
console.log(decode(short))

var short = encode("https://leetcode.com/problems/design-tinyurl3")
console.log(short)
console.log(decode(short))