// 1
val a = <fred>
  <greg>
    <rob></rob>
  </greg>
</fred> <dims>
  <rob></rob>
</dims>

a(0) //returns fred element
a(0)(0) //also returns fred element

// in first case a is NodeBuffer so a(0) return element #0, a(1) - element number 1
// in second case a(0) is Elem which NodeSeq with one element in it
// a(0)(0), a(0)(0)(0) will return the same element

// 2
/*
<ul>
  <li>Opening bracket: [</li>
  <li>Closing bracket: ]</li>
  <li>Opening brace: {</li>
  <li>Closing brace: }</li>
</ul>
*/

val fixed =
  <ul>
    <li>Opening bracket: [</li>
    <li>Closing bracket: ]</li>
    <li>Opening brace: {{</li>
    <li>Closing brace: }}</li>
  </ul>
