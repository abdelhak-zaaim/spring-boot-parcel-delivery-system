/*
 * **
 *  * @project : SuiviColis
 *  * @author : Abdelhak Zaaim
 *  * @email : abdelhakzammii@gmail.com
 *  * @created : 23/04/2024, 18:18
 *  * @modified : 23/04/2024, 18:05
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  *
 *  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *  **
 */

package com.suivi.colis.suivicolis.models.enums;

public enum Role {
    USER,
    EMPLOYEE,
    CUSTOMER,
    DELIVERY_MAN,
    TRANSPORTER,
    AGENCY;
    public static final String EMPLOYEE_STR = "EMPLOYEE";
    public static final String USER_STR = "USER";
    public static final String DELIVERY_MAN_STR = "DELIVERY";
    public static final String AGENCY_OWNER_STR = "AGENCY";
    public static final String CUSTOMER_STR = "CUSTOMER";
    public static final String TRANSPORTER_STR = "TRANSPORTER";
}