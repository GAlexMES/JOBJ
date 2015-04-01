# JOBJ
A libary/parser for reading .obj files in Java.


# Support
| Feature | Support (parsing) | Support (rendering)
| :------ | :-----: | :-----: |
| Vertex data |
| (v) geometric vertices |  X | |
| (vt) texture vertices | X | |
| (vn) vertex normals | C | |
| (vp) parameter space vertices | X ||
| (deg) degree |||
| (bmat) basis matrix |||
| (step) step size |||
| Elements |
| (p) point |X||
| (l) line |X||
| (f) face | X ||
| (curv) curve |||
| (curv2) 2D curve |||
| (surf) surface |||
| Free-form curve/surface body statements |
| (parm) parameter values |||
| (trim) outer trimming loop |||
| (hole) inner trimming loop |||
| (scrv) special curve |||
| (sp) special point |||
| (end) end statement |||
| Connectivity between free-form surfaces |
| (con) connect |||
| Grouping |
| (g) group name | X ||
| (s) smoothing group | X ||
| (mg) merging group |||
| (o) object name | X ||
| Display/render attributes |
| (bevel) bevel interpolation |||
| (c_interp) color interpolation |||
| (d_interp) dissolve interpolation |||
| (lod) level of detail |||
| (usemtl) material name | X ||
| (mtllib) material libary | X ||
| (shadow_obj) shadow casting |||
| (trace_obj) ray tracing |||
| (ctech) curve approximation technique |||
| (stech) surface approximation technique |||
| Others |||
| (#) Comments | X ||

Features from http://www.martinreddy.net/gfx/3d/OBJ.spec
