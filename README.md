# JOBJ
A libary/parser for reading .obj files in Java.


# Support
| Feature | Support (parsing) | Support (rendering)
| :------ | :-----: | :-----: |
| Vertex data |
| (v) geometric vertices |  X | |
| (vt) texture vertices | | |
| (vn) vertex normals | | |
| (vp) parameter space vertices (vp) |||
| (deg) degree |||
| (bmat) basis matrix |||
| (step) step size |||
| Elements |
| (p) point |||
| (l) line |||
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
| (s) smoothing group |||
| (mg) merging group |||
| (o) object name | X ||
| Display/render attributes |
| (bevel) bevel interpolation |||
| (c_interp) color interpolation |||
| (d_interp) dissolve interpolation |||
| (lod) level of detail |||
| (usemtl) material name |||
| (mtllib) material libary |||
| (shadow_obj) shadow casting |||
| (trace_obj) ray tracing |||
| (ctech) curve approximation technique |||
| (stech) surface approximation technique |||

Features from http://www.martinreddy.net/gfx/3d/OBJ.spec
