/*
 * **
 *  * @project : SuiviColis
 *  * @author : Abdelhak Zaaim
 *  * @email : abdelhakzammii@gmail.com
 *  * @created : 23/04/2024, 18:13
 *  * @modified : 23/04/2024, 18:13
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  *
 *  * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *  **
 */

package com.suivi.colis.suivicolis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data

public class trackParcel {

    @Id
    private Long id;

    // the parcel that this track belongs to
    @ManyToOne
    @JoinColumn(name = "idParcel", referencedColumnName = "id")
    private Parcel idParcel;

    // the status of the parcel as a string
    private String status;
    private Date date;


    // the curent user who the parcel located with (for the delivery, or the user who the parcel is with) ....
    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User idUser;


}
